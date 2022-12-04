# 💸 캐시 API 사용방법 💸


## ⚙️ 개발 환경 ⚙️
- JDK(Java Development Kit) 17
- SPIGOT 1.19.2


## 💵 캐시 활용하기 💵
```java
public class Example() {
  CashValueData cashValueData = new CashValueData();

  cashValueData.depositCash(target, amount);
  cashValueData.withdrawCash(target, amount);
  cashValueData.setCash(target, amount);
  cashValueData.getCash(target)
}

```
