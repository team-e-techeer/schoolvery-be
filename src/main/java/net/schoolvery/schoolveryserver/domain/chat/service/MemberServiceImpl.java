package net.schoolvery.schoolveryserver.domain.chat.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.domain.chat.repository.MemberRepository;
import net.schoolvery.schoolveryserver.domain.chat.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    @Override
    public void addMembers(UUID room_id, UUID member_id){
        log.info("Start Searching Members");
        Room r = roomRepository.getByRoomId(room_id);
        if (r.getMember().isEmpty()){
            addMember(r,member_id);
        }
        else{
            boolean flag = false;
            for (Member a : r.getMember()) {
                if (a.getMember_id().equals(member_id)){
                    flag = true;
                    break;
                }
            }
            if (flag == false){
                addMember(r,room_id);
            }
        }
    }

    public void addMember(Room r, UUID member_id){
        Member member = Member.builder()
                .room(r)
                .member_id(member_id)
                .build();
        memberRepository.save(member);
        r.getMember().add(member);
    }

}
