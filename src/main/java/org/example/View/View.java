package org.example.View;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.example.Message.Message;
import org.example.Model.Model;
import org.example.Observ.Observer;

public class View implements Observer<Message> {
    Model model;

    public View(){
        this.model = new Model(this);
    }

    @Override
    public void update(Message message) {

    }
}
