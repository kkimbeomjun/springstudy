package beomjun.preject1.repositorty;

import beomjun.preject1.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private static long squence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++squence); // id 셋팅
        store.put(member.getId(), member); // 스토어 저장
        return  member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
      store.clear();
    }

}
