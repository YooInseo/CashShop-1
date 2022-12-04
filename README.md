# 💸 캐시 + 캐시상점 API 💸


## ⚙️ 개발 환경 ⚙️
- JDK(Java Development Kit) 17
- SPIGOT 1.19.2


## 💵 캐시 활용하기 💵
```java
public class Example() {

  CashAPI cashAPI = new CashAPI();

  cashAPI.depositCash(target, amount);
  cashAPI.withdrawCash(target, amount);
  cashAPI.setCash(target, amount);
  cashAPI.getCash(target)
}
```

## 🛒 캐시상점 활용하기 🛒
```java
public class Example implements Listener {

  @EventHandler
  public void purchaseEvent(CashShopPurchaseEvent event) {
  
    Player player = event.getPlayer();
    CashShopPurchaseEvent.PurchaseType purchaseType = event.getPurchaseType();
    
    if (purchaseType == CashShopPurchaseEvent.PurchaseType.SET) {
        player.sendMessage("64개를 구매했습니다!");
    }
    
    if (purchaseType == CashShopPurchaseEvent.PurchaseType.SINGLE) {
        player.sendMessage("1개를 구매했습니다!");
    }
    
    
    @EventHandler
    public void sellEvent(CashShopSellEvent event) {
    
      Player player = event.getPlayer();
      CashShopPurchaseEvent.SellType sellType = event.getSellType();
    
      if (SellType == CashShopSellEvent.SellType.SET) {
          player.sendMessage("64개를 판매했습니다!");
      }
    
      if (SellType == CashShopSellEvent.SellType.SINGLE) {
          player.sendMessage("1개를 판매했습니다!");
      }
}
```
    


