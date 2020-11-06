# PrimeNumber2.1
第二代的演算法具有服務架構
1. 資料封裝為Java bean 物件
1. OptimusPrime 負責管理素數表、產生Java bean物件
1. Cheetor 物件負責資料推斷
1. 優化:素數數列載入緩存區使用，緩存區容量可自訂
1. 預設支援100,000以內的自然數。有需要請自行更改上限upper=√(想要的自然數上限)
