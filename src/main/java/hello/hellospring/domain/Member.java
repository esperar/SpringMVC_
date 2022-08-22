package hello.hellospring.domain;

public class Member {

    // 각 회원마다 아이디를 부여해 구별합니다.
    private Long id;
    // 회원이 입력하는 name 입니다.
    private String name;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
