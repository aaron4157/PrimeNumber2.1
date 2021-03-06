# PrimeNumber2.1
第二代的演算法具有服務架構 [數學課由此。慎入](MathematicsHere.md)

* 資料封裝為Java bean 物件
* 使用範例在demo包、功能在maximal包
* OptimusPrime 是資料應對物件(DAO)
* Rattrap 物件打包資料為 Java bean
* Rhinox 物件負責文字訊息加密與解密
* 優化:素數數列預先載入cache，視需要分派複本
* 預設支援10,000,000,000以內的自然數因數分解。有需要請自行更改上限upper=√(想要的自然數上限) 產生新的檔案
* Delegate: 尋找另外兩個內部物件 dinobot 與 cheetor

### 新版特色(2021.3.3)
* 引入虛擬資料表的概念。素數數列作為核心有持久化(persistence)，其它"欄位"推算得到。
* 引入依賴注入(DI)的作法，將數據與算法完全分開
* 驗算(verify) 強版本Goldbach conjecture，為所有偶數計算素數對
* 非對稱加密/解密概念示範 

**	聲明：演示代碼安全性不足，請使用正規專業軟體保護機敏資訊。**

* 引入Java SE8 的串流(Stream)類別
* 引入Java SE8 的Base64編碼類別

### 基於素數表各種算法
前代嘗試過動態的計算方式。後來發現，多做些前置工作，建立素數參考表，比較方便。這是以(靜態)空間換取時間。

### 關於RSA加密(RSA encryption)
先說感想。RSA的加密/解密運算占用大量記憶體。即使持有金鑰，也很難在小程式使用。自製實用的RSA加密/解密機已經很困難。而自行生產金鑰、甚至破譯金鑰，家用PC的計算力顯然不足以勝任、這種技術的持有者，通常鎖定大目標如跨國企業、大國政府；平民的秘密通常不引起他們的興趣。
例如、256<sup>128</sup> 就可以塞爆**double**變數的記憶體，中文碼(>13,000)加密需要其它技術支援。
本例使用unicode編碼作為文字/數字對應，因為這是Java原生的字符表，JVM支援**char**↔**int**原生轉換。受限於軟體的算力，金鑰只有一只、而且不大，只能加密/解密拉丁文字。
> 預設範例： 將訊息
"Hello world!!" 加密成
"ÖúggßǗ[ßōgũÿÿ" 再解密。

>任何跟Rhinox物件取得公鑰的人，都可以發送加密訊息給他。

##### 實際應用的RSA：
* 通訊雙方交換公鑰，進行雙向秘密通信
* 金鑰是超級大數，記憶體佔位2048 bits以上
* 對字串而非字元加密
* 經常更換金鑰 ex一段文字切成3組、以3只金鑰加密

### 關於Base64編碼
base64編碼方式概述如下：
1. 字串解析成為字元組
1. 字元組緩衝為6位元字符組(值域 = 0 - 63)
1. 每個字符映射到64個拉丁文字之一(A-Z, a-z, 0-9, +, /)

2021.3.3版本以base64編碼將訊息做預處理、再以RSA公鑰加密。
選用base64編碼，有以下考量：
* 這是廣泛用於網路傳輸文字訊息的預處理作法
* Java有完整的Base64套件，編碼/解碼方便

##### Base64字符大小6bit(2<sup>6</sup> = 64)，而unicode字符大小16bit(2<sup>16</sup> = 65,536)。預期，unicode字串經Base64編碼後，長度變為8/3 ~ 2.67倍；不過，Java 的Base64套件將unicode字符預處理為4字節(每個字節4bit為16進位編碼)，所以編碼後實際長度是原來的(1, 2, 3, 4)倍。 以中文字來說，總是轉譯成4碼Base64字符，所以轉譯規則有跡可循、頻率分析有機會破譯。不過，標點符號或中英夾雜(插入小單位)的狀況，仍然打亂轉譯的表現規則。

> 範例：原始訊息
"玄之又玄 ，眾妙之門"

>轉碼為
"546E5LmL5Y+I546EIO+8jOecvuWmmeS5i+mWgA=="
(注意兩個"之"字編碼不同)

>再加密為
"ēǡǃvēƜdƜēPx¦ēǡǃv¦ćxĻęćúŜddúûēüxdŜ-ĸ44"