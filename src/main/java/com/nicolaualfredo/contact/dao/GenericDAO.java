/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import java.util.List;

/**
 *
 * @author nicolaualfredo
 * @param <T>
 */

public interface GenericDAO<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

    T findById(Integer id);

    List<T> findAll();

}
