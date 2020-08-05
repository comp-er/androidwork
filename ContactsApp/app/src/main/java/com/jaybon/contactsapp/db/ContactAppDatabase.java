package com.jaybon.contactsapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jaybon.contactsapp.db.model.Contact;
import com.jaybon.contactsapp.repository.ContactRepository;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {
    public abstract ContactRepository contactRepository();
}
