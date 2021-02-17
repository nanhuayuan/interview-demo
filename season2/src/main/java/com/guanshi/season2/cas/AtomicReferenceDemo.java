package com.guanshi.season2.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author poi 2021/2/11 10:17
 * @version 1.0
 * 2021/2/11 10:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    String userName;
    int age;

}
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User zs = new User("z3", 22);
        User li4 = new User("lis", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        System.out.println(atomicReference.compareAndSet(zs, li4) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, li4) + "\t " + atomicReference.get().toString());


    }
}
