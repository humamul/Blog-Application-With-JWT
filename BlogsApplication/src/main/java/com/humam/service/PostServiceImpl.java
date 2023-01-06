package com.humam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.PostDto;
import com.humam.io.PostResponse;
import com.humam.model.Category;
import com.humam.model.Post;
import com.humam.model.Role;
import com.humam.model.User;
import com.humam.repository.CategoryRepo;
import com.humam.repository.PostRepo;
import com.humam.repository.RolesRepo;
import com.humam.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private RolesRepo rolesRepo;
	
	public User getCurrentUser()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
	
		String username = userDetails.getUsername();
		Optional<User> optUser=userRepo.findByUsername(username);
		
		return optUser.get();
	}


	@Override
	public Post createPost(PostDto postDto, Integer categoryId) {
		
		User user=getCurrentUser();	    
        Optional<Category> optCategory=categoryRepo.findById(categoryId);

		if(!optCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		Post post =new Post();
		post.setCategory(optCategory.get());
		post.setUser(user);
		post.setContent(postDto.getContent());
		post.setDate_added(LocalDate.now());
		post.setTitle(postDto.getTitle());

		return postRepo.save(post);

	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		
		User user=getCurrentUser();
		List<Post> list=user.getPosts();
		
		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		
		Post post=optional.get();
		if(!list.contains(post))
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());

		return postRepo.save(post);
	}
	@Override
	public Post updateCategoryOfPost(Integer postId, Integer categoryId) {
		
		User user=getCurrentUser();
		List<Post> list=user.getPosts();

		Optional<Post> optional1=postRepo.findById(postId);
		if(!optional1.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}
		Optional<Category> optional2=categoryRepo.findById(categoryId);
		if(!optional2.isPresent())
		{
			throw new ResourceNotFoundException("Category", "Id", categoryId);
		}

		Post post=optional1.get();
		
		if(!list.contains(post))
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}
		Category category=optional2.get();

		post.setCategory(category);

		return postRepo.save(post);
	}

	@Override
	public Post deletePost(Integer postId) {
		
		User user=getCurrentUser();
		List<Post> list=user.getPosts();
		
		System.out.println(list);
		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}
		
		System.out.println("*****************************************************************************");

//		Optional<Post> AdminOpt=postRepo.findById(postId);
//		Post admin=AdminOpt.get();
		Optional<Role> role = rolesRepo.findById(1);
		Role roleAdmin = role.get();
		System.out.println(roleAdmin);
		Post post=optional.get();
		if(user.getRoles().contains(roleAdmin))
		{
			user.getPosts().remove(post);
			userRepo.save(user);
			postRepo.delete(post);
			
			
		}
		else
		{
			if(!list.contains(post))
			{
				throw new ResourceNotFoundException("Post", "Id", postId);
			}
			
			user.getPosts().remove(post);
			userRepo.save(user);
			postRepo.delete(post);
			
		}
		

		return post;
	}



	@Override
	public List<Post> getAllPost() {

		return postRepo.findAll();

	}
    @Override
	public PostResponse getAllPostPagination(Integer pageSize, Integer pageNumer,String sortBy) {

    	org.springframework.data.domain.Pageable p=PageRequest.of(pageNumer, pageSize,org.springframework.data.domain.Sort.by(sortBy));

    	Page<Post> posts=postRepo.findAll(p);
    	List<Post> list=posts.getContent();

    	PostResponse response=new PostResponse();
    	response.setContent(list);
    	response.setLastPage(posts.isLast());
    	response.setPageNumber(posts.getNumber());
    	response.setPageSize(posts.getSize());
    	response.setTotalElements(posts.getTotalElements());
    	response.setTotalPages(posts.getTotalPages());

    	return response;



	}


	@Override
	public Post getPostById(Integer postId) {

		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		return optional.get();

	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {

		Optional<Category> optional=categoryRepo.findById(categoryId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		List<Post> list= postRepo.findByCategory(optional.get());
		System.out.println(list+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return list;

	}

	@Override
	public List<Post> getPostByUserId(Integer userId) {
		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		List<Post> list= postRepo.findByUser(optional.get());

		return list;
	}


}
