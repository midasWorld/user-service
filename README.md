# 인사 관리 시스템

사원의 정보를 등록하고 근태 관리까지 될 수 있도록 하는 프로젝트 입니다.
<br>
그리고 이 프로젝트를 시작으로 전자 결재 프로젝트를 진행할 예정입니다.

### 인사 관리 정보

1. 사원
2. 직급 (class)
   - 사원, 주임, 대리, 과장, 차장, 부장, 이사, 상무, 전무, 부사장, 사장
3. 직책 (position)
   - 파트장, 팀장, 실장, 본부장, 사업부장, CTO, CEO, CFO, COO 등
4. 부서 (department)
   - 총무, 인사, 법무, 재무, 전산 등

### 근태 관리 정보

1. 근태

   ```sql
   create table time_attendance (
      id             bigint    NOT NULL AUTO_INCREMENT,      # pk
      user_id        bigint    NOT NULL,                     # 회원 Id
      start_worktime datetime  NOT NULL,                     # 출근시간
      end_worktime   datetime  NOT NULL,                     # 퇴근시간
      work_hour      int       NOT NULL,                     # 근무시간
      createdDate    datetime  NOT NULL,                     # 생성일시
      ModifiedDate   datetime,
      PRIMARY KEY (id),
      CONSTRAINT fk_attendance_to_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT
   )
   ```

2. 휴식시간(점심시간, 저녁시간 등)
   ```sql
   create table break_time (
      id             bigint    NOT NULL AUTO_INCREMENT,      # pk
      start_worktime time      NOT NULL,                     # 시작시간
      end_worktime   time      NOT NULL,                     # 종료시간
      createdDate    datetime  NOT NULL,                     # 생성일시
      ModifiedDate   datetime,
      PRIMARY KEY (id),
   )
   ```
