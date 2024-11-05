### 📝 기능 명세서
---

### 1. 상품 관리 기능

- [x] **상품 목록 불러오기 기능**
    - `products.md` 파일에서 상품 정보를 읽어와 각 상품의 이름, 가격, 재고, 프로모션 정보를 시스템에 로드한다.

- [ ] **재고 업데이트 기능**
    - 상품이 구매될 때마다 구매한 수량만큼 재고에서 차감하고, 재고가 부족하면 오류 메시지를 출력한다.

- [ ] **재고 상태 출력**
    - 고객이 구매 화면으로 이동할 때마다 현재 보유한 상품 목록과 재고 상태를 출력한다.

### 2. 결제 및 할인 계산 기능

- [ ] **총구매액 계산**
    - 구매할 각 상품의 가격과 수량을 곱하여 총구매액을 계산한다.

- [ ] **프로모션 할인 계산**
    - 프로모션이 적용 가능한 경우, 프로모션 수량 조건을 만족할 때마다 무료 상품을 제공한다.
    - 프로모션 재고가 충분하지 않은 경우, 재고가 부족한 만큼 정가 결제 여부를 고객에게 물어본다.
    - 프로모션 재고와 일반 재고가 나뉘어 있으며, 프로모션 재고가 우선 차감된다.

- [ ] **멤버십 할인 적용**
    - 멤버십 회원의 경우 프로모션이 적용되지 않은 금액의 30%를 추가로 할인한다.
    - 할인 금액은 최대 8,000원으로 제한된다.

- [ ] **최종 결제 금액 산출**
    - 총구매액에서 프로모션 할인과 멤버십 할인을 뺀 최종 결제 금액을 산출한다.

### 3. 사용자 입력 처리 기능

- [ ] **상품 및 수량 입력**
    - 고객이 `[상품명-수량]` 형식으로 입력한 상품과 수량 정보를 처리한다.

- [ ] **프로모션 혜택 안내**
    - 고객이 프로모션 조건에 미치지 않는 수량을 입력한 경우, 프로모션 혜택을 받기 위해 추가할 수량 여부를 묻는다.
    - 예: `현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)`

- [ ] **정가 결제 여부 확인**
    - 프로모션 재고가 부족하여 일부 수량이 정가로 결제되는 경우, 고객에게 정가로 결제할지 물어본다.
    - 예: `현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)`

- [ ] **멤버십 할인 적용 여부 확인**
    - 멤버십 할인을 받을지 고객에게 묻는다.
    - 예: `멤버십 할인을 받으시겠습니까? (Y/N)`

- [ ] **추가 구매 여부 확인**
    - 결제 완료 후 추가 구매 여부를 고객에게 묻는다.
    - 예: `감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)`

### 4. 오류 처리 기능

- [ ] **입력 형식 오류**
    - `[상품명-수량]` 형식이 맞지 않거나 잘못된 형식일 경우 오류 메시지를 출력한다.
    - 예: `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`

- [ ] **존재하지 않는 상품 오류**
    - 입력한 상품이 존재하지 않는 경우 오류 메시지를 출력한다.
    - 예: `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`

- [ ] **재고 부족 오류**
    - 재고 수량보다 많은 수량을 입력한 경우 오류 메시지를 출력한다.
    - 예: `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`

- [ ] **기타 입력 오류**
    - 위의 경우를 제외한 모든 잘못된 입력에 대해 오류 메시지를 출력한다.
    - 예: `[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`

### 5. 영수증 출력 기능

- [ ] **영수증 출력**
    - 고객이 구매한 상품명, 수량, 가격을 정렬하여 출력한다.
    - 증정된 무료 상품 내역도 함께 출력한다.

- [ ] **금액 정보 요약**
    - 총구매액
    - 행사할인
    - 멤버십할인
    - 내실돈(최종 결제 금액)

### 6. 유틸 기능

- [x] 파일에서 헤더를 제외한 라인을 읽고 리스트로 반환한다.