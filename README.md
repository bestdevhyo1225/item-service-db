# Item Service DB

## DTO의 위치?

`DTO` 를 마지막으로 제공하는 곳이 어디냐에 따라 `DTO` 를 해당 패키지에 위치하면 된다. 예를 들면, `Repository` 레이어에서 제공하는 경우에는 `repository` 패키지에 위치해야 한다.

```java
public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

}
```

`ItemUpdateDto`, `ItemSearchCond` 는 `DTO` 인데 `Repository` 레이어에서 마지막으로 제공하기 때문에 `repository` 패키지 내부에 위치해야 한다.

만약에 `Service` 레이어에서 `DTO` 를 마지막으로 제공한다면, `service` 패키지 내부에 `DTO` 들을 위치 시켜두면 된다.

## @EventListener

### ApplicationReadyEvent

사용법은 `@EventListener(ApplicationReadyEvent.class)` 형식으로 작성하면 된다. AOP를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출된다.
보통 `@PostConstruct` 를 사용해서 초기화 작업을 진행하기도 하는데, AOP 부분이 다 적용(처리)되지 않은 시점에 호출될 수 있기 때문에 간혹 문제가 발생할 수 있다.

## 인터페이스를 테스트하자

구현체 대신에 `인터페이스` 를 테스트 하게 되면, 나중에 다른 구현체로 변경되었을 때 해당 구현체가 잘 동작하는지 같은 테스트로 편리하게 검증할 수 있다.

## 테스트의 중요한 원칙

- 테스트는 다른 테스트와 격리해야 한다.
- 테스트는 반복해서 실행할 수 있어야 한다.

## @Transactional 원리

`@Transactional` 은 로직이 성공적으로 수행되면, `Commit` 하도록 동작한다. 그런데 테스트에서 사용하면, 아주 특별하게 동작한다. `@Transactional` 이 테스트에 있으면,
스프링은 테스트를 트랜잭션 안에서 실행하고, 테스트가 끝나면 트랜잭션을 자동으로 `Rollback` 한다.

## @Repository 기능

- `@Repository` 가 붙은 클래스는 컴포넌트 스캔의 대상이 된다.
- `@Repository` 가 붙은 클래스는 예외 변환 AOP의 적용 대상이 된다.
    - 스프링과 JPA를 함께 사용하는 경우, 스프링은 JPA 예외 변환기 `PersistenceExceptionTranslator` 를 등록한다.
    - 예외 변환 AOP 프록시는 JPA 관련 예외가 발생하면 JPA 예외 변환기를 통해 발생한 예외를 스프링 데이터 접근 예외로 변환한다.
    - `PersistenceException` -> `DataAccessException` 으로 변환해준다.
