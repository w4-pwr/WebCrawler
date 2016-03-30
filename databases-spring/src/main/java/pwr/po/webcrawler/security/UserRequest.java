package pwr.po.webcrawler.security;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rafal on 2016-03-25.
 */

@JsonAutoDetect
@Getter
@Setter
public class UserRequest {
    @JsonProperty("username")
   private String username;

    @JsonProperty("password")
    private String password;
}
