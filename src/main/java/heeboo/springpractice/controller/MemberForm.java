package heeboo.springpractice.controller;

public class MemberForm {
    private String name; //createMemberForm의 input type의 name="name"과 매칭
    //createMemberForm에서 입력시 스프링이 setName으로 값을 넣어주고 가져올땐 getName으로 가져온다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
