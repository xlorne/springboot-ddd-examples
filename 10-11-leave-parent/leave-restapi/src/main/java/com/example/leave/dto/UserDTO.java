package com.example.leave.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

public class UserDTO {


    @Setter
    @Getter
    public static class ListRequest{

        private int current;
        private int pageSize;

        public PageRequest toPageRequest(){
            return PageRequest.of(current>0?current-1:0,pageSize);
        }
    }

}
