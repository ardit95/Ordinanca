package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Message;
import ejb.Staff;

public interface MessageInterface {

    Message create(Message message) throws AppException;

    void edit(Message message) throws AppException;

    void remove(Message message);

    List<Message> findAll();

    List<Message> findByReciever(Staff currentUser);

    List<Message> findBySenderAndReciever(Staff reciever, Staff sender);

    int countUnseenMessagesForUser(Staff currentUser);

    int countMessagesForUser(Staff currentUser);

    int countUnseenMessagesForSpecificUser(Staff currentUser, Staff messageFrom);
    
    List<Message> checkUnseenMessages(Staff currentUser);

    
}
