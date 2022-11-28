# HOTEL MANAGEMENT PROJECT

### LƯU Ý (cho Tester - Viewer):
- Nhớ vào file JdbcUtils.jav (package com.linhvu.conf), đổi dòng 33 thành 

```
return DriverManager.getConnection("jdbc:mysql://localhost/{tên database}, "{tên user mysql}", "{pass mysql}");
```