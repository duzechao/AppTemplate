package github.dzc.apptemplate.event;

import github.dzc.apptemplate.model.Person;

/**
 * Created by dzc on 15/11/7.
 */
public class MessageEvent {
    private Person person;
    private String msg;

    public MessageEvent(Person person, String msg) {
        this.person = person;
        this.msg = msg;
    }

    public Person getPerson() {
        return person;
    }

    public String getMsg() {
        return msg;
    }
}
