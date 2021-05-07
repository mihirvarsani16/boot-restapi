package com.api.book.bootrestbook.controller;

import java.util.List;
import java.util.Optional;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {
  // @RequestMapping(value="/books",method = RequestMethod.GET)
 // @ResponseBody

 @Autowired
private BookService bookService;

//get all books handler
   @GetMapping("/books")
   public ResponseEntity<List<Book>> getBooks(){

  
    List<Book> list =bookService.getAllBooks();
      if(list.size()<=0){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
     return ResponseEntity.status(HttpStatus.CREATED).body(list);
     // return "this is testing book first";

  }  


  /////////////////////////////////////////
  //get one book
  @GetMapping("/books/{id}")
public ResponseEntity<Book> getBook(@PathVariable("id") int id){

 Book book =bookService.getBookId(id);
 if(book==null){
   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 }
return ResponseEntity.of(Optional.of(book));
}

//add one book
@PostMapping("/books")
public ResponseEntity<Book> addBook(@RequestBody Book book){
Book b= null;

try{
  b = bookService.addBook(book);
System.out.println(book);
return ResponseEntity.of(Optional.of(b));
}catch(Exception e){
e.printStackTrace();
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}

}

//delete book handler

@DeleteMapping("/books/{bid}")
public ResponseEntity<?> deleteBook(@PathVariable("bid") int bid){
try{
this.bookService.deleteBook(bid);
return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}catch(Exception e){
  e.printStackTrace();
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
}

//update book handler
@PutMapping("/books/{id}")
public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id){
try{

this.bookService.updateBook(book,id);
return ResponseEntity.of(Optional.of(book));
}catch(Exception e){
  e.printStackTrace();
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
}

}