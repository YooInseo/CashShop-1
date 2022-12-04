![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=캐시 + 캐시상점fontSize=90&animation=fadeIn&fontAlignY=38&desc=Decorate%20GitHub%20Profile%20or%20any%20Repo%20like%20me!&descAlignY=51&descAlign=62)

<p align='center'> 마인크래프트 | 캐시 + 캐시상점 플러그인  </p>
<p align='center'>
  <a href="https://github.com/idkNicks">
    <img src="https://img.shields.io/badge/IDEA%20ISSUE%20-%23F7DF1E.svg?&style=for-the-badge&&logoColor=white"/>
  </a>
  <a href="#demo">
    <img src="https://img.shields.io/badge/DEMO%20-%234FC08D.svg?&style=for-the-badge&&logoColor=white"/>
  </a>
</p>
# 💸 캐시 + 캐시상점 API 💸


## ⚙️ 개발 환경 ⚙️
- JDK(Java Development Kit) 17
- SPIGOT 1.19.2
- 문의: kcin#9128


## 💵 캐시 활용하기 💵
```java
public class Example() {

  CashAPI cashAPI = new CashAPI();

  cashAPI.depositCash(target, amount); // 캐시 지급
  
  cashAPI.withdrawCash(target, amount); // 캐시 차감
  
  cashAPI.setCash(target, amount); // 캐시 설정
  
  cashAPI.getCash(target) // 캐시 가져오기
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
    


