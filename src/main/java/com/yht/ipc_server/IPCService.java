package com.yht.ipc_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.yht.ipc.Book;
import com.yht.ipc.IBookManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IPCService extends Service {
    List<Book> books;
    int id;

    @Override
    public void onCreate() {
        books = new ArrayList<>();
        books.add(new Book(id++, "海贼王"));
        books.add(new Book(id++, "上古卷轴"));
    }

    public IPCService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return iBookManager;
    }

    IBookManager.Stub iBookManager = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            try {
                Field bid = Book.class.getDeclaredField("bookId");
                bid.setAccessible(true);
                bid.setInt(book, id++);
                books.add(book);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    };
}
