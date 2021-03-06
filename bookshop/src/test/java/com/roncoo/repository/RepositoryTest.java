/**
 * 
 */
package com.roncoo.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.lesson.domain.Author;
import com.lesson.domain.Book;
import com.lesson.domain.Ebook;
import com.lesson.domain.PrintBook;
import com.lesson.repository.AuthorRepository;
import com.lesson.repository.BookRepository;
import com.lesson.repository.PrintBookRepository;
import com.roncoo.BaseTest;

/**
 * @author zhailiang
 *
 */
public class RepositoryTest extends BaseTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PrintBookRepository printBookRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Test
	public void test1(){
		 
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Book book = bookRepository.findOne(1L);		
		book.setName("美女与野兽");
		bookRepository.saveAndFlush(book);;
		System.out.println("success");
		transactionManager.commit(status);
		
	}
	
	@Test
	public void test6() {
		Author author = new Author();
		author.setEmail("xxx");
		authorRepository.saveAndFlush(author);
	}
	
	@Test
	public void test5() {
		Book book = bookRepository.findOne(1L);	
		book.setName("xxx");
		bookRepository.saveAndFlush(book);
	}
	
	@Test
	public void test2(){
		bookRepository.findAll();
		bookRepository.findAll();
	}
	
	@Test
	public void test3(){
//		Book book = bookRepository.findOne(1L);	
//		System.out.println(book.getCategory().getName());
		
		Book book = bookRepository.findByName("战争与和平");
		System.out.println(book.getCategory().getName());
	}
	
	@Test
	public void test4(){
	
		PrintBook printBook = new PrintBook();
		printBook.setName("1");
		bookRepository.save(printBook);
		
		Ebook ebook = new Ebook();
		ebook.setName("2");
		bookRepository.save(ebook);
		
		List<Book> books = bookRepository.findAll();
		books.stream().forEach(book -> System.out.println(book.getClass().getSimpleName()));
		
		List<PrintBook> pbooks = printBookRepository.findAll();
		pbooks.stream().forEach(book -> System.out.println(book.getClass().getSimpleName()));
		
	}
	
}
