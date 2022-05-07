package net.schoolvery.schoolveryserver.domain.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import net.schoolvery.schoolveryserver.domain.chat.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    public final MemberRepository memberRepository;
    @Override
    public void addMembers(UUID room_id, UUID member_id) {
        Member member;
        //memberRepository.create()
    }
}
