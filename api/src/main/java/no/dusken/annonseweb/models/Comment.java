package no.dusken.annonseweb.models;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Emilie2
 * Date: 05.10.11
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class Comment {
    long ID;
    String text;

    Date created_date;
    String created_user;
    Date lastedited_date;
    String lastedited_user;
}
