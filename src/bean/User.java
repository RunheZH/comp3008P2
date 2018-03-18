package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private List<Password> password_list = Collections.synchronizedList(new ArrayList<Password>());
    private String token;
}
