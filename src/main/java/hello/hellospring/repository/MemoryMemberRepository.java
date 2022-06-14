package hello.hellospring.repository;

import java.util.*;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); // 저장소
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
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

	// junit test 진행 시 테스트 순서가 보장이 되지 않기 때문에 이전 테스트에서 남은 기록들을 clear해주는 코드를 작성하여야 한다.
	public void clearStore() {
		store.clear();
	}
}
