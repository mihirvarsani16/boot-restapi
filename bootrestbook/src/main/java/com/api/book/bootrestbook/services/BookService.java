package com.api.book.bootrestbook.services;

import java.util.List;

import com.api.book.bootrestbook.Dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//     private static List<Book> list=new ArrayList<>();

//    static{

//      list.add(new Book(12, "java reference", "any"));
//      list.add(new Book(157, "java reference advance", "xyz"));
//      list.add(new Book(127657, "html reference", "vhvh"));

//  }


//get  all books
public List<Book> getAllBooks(){
List<Book> list =(List<Book>) this.bookRepository.findAll();

    return list;
}

//get get singal book by id

public Book getBookId(int id){

    Book book = null;
    try{
    //book=list.stream().filter(e->e.getId()==id).findFirst().get();
  book=this.bookRepository.findById(id);    
}catch(Exception e){
        e.printStackTrace();
    }
    return  book;
}

//adding the book
public Book addBook(Book book){
  Book result =bookRepository.save(book);
    //list.add(book);
  return result;
    }

//delete book
public void deleteBook(int bid){
//list=list.stream().filter(book ->book.getId()!=bid).
//collect(Collectors.toList());
bookRepository.deleteById(bid);
}


//update the 
public void updateBook(Book book,int id){
// list = list.stream().map(b->{
// if(b.getId()==id){
//     b.setTitle(book.getTitle());
//     b.setAuthor(book.getAuthor());
// }
// return b;
// }).collect(Collectors.toList());
book.setId(id);
bookRepository.save(book);
}



}