
# HOTEL MANAGEMENT PROJECT


### LƯU Ý (cho Tester - Viewer):
- Nhớ vào file JdbcUtils.jav (package com.linhvu.conf), đổi dòng 33 thành 

```
return DriverManager.getConnection("jdbc:mysql://localhost/{tên database}", "{tên user mysql}", "{pass mysql}");
```

## MÔ TẢ HỆ THỐNG:
- Màn hình chính sẽ là giao diện gồm các nút **Đăng nhập**, **Đăng ký**, 2 ô nhập chọn **ngày nhận và trả phòng** kèm 1 nút **Tìm kiếm thông tin phòng** cho khách.
- Hệ thống sẽ yêu cầu người dùng (nhân viên và khách hàng) đăng nhập hoặc đăng ký tài khoản trước khi tương tác với hệ thống.
- Khi chọn **Đăng nhập**, màn hình chuyển sang giao diện đăng nhập, yêu cầu người dùng cung cấp thông tin *vai trò*, *CCCD/CMND* và *mật khẩu* gắn với tài khoản.
  - Nếu người dùng nhập thiếu bất cứ thông tin nào, sẽ xuất hiện dòng thông báo **"Please fill out all field!"**
  - Nếu người dùng nhập đầy đủ thông tin nhưng không chọn vai trò (nhân viên/khách hàng), sẽ xuất hiện dòng thông báo **"Please choose your account type!"**
  - Nếu CCCD/CMND hay mật khẩu người dùng nhập vào không trùng khớp với cơ sở dữ liệu thì sẽ xuất hiện dòng thông báo **"UserID or Password incorrect!"**
  - Khi thông tin đăng nhập chính xác, chuyển sang cửa sổ chính tương ứng với từng vai trò người dùng.
- Khi chọn **Đăng ký tài khoản** thì cửa sổ đăng ký sẽ hiện lên, khách hàng sẽ cần cung cấp số CCCD/CMND, họ và tên, ngày sinh, sđt và mật khẩu cho tài khoản.
  - Nếu số CCCD/CMND người dùng nhập vào không hợp lệ (không phải là số hoặc đã tồn tại trong hệ thống), sẽ hiển thị hộp thoại thông báo **"Your ID is invalid or used by another account."**
  - Nếu người dùng để trống bất cứ thông tin nào, sẽ hiển thị hộp thoại thông báo **"Please fill out all field."**
  - Người dùng không được phép chọn ngày sinh không hợp lệ: là ngày hiện tại và tương lai, chỉ được phép chọn ngày trong quá khứ.
  - Nếu sđt nhập vào không hợp lệ: không phải số, không bắt đầu bằng số '0', tồn tại khoảng trắng, không chứa đủ 10 ký tự; sẽ hiển thị hộp thoại thông báo **"Phone number invalid!"**
  - Nếu mật khẩu người dùng tạo không hợp lệ, sẽ hiển thị hộp thoại thông báo **"Your password must be 8-16 characters!"** hoặc **"Password must contains lower, upper, special character and a number."**
  - Khi thông tin đăng ký chính xác, sẽ hiển thị hộp thoại thông báo **"Sign up success!"**
### 1. Vai trò khách hàng:

### 2. Vai trò nhân viên khách sạn:



### CÁC RÀNG BUỘC TRONG DỮ LIỆU ĐẦU VÀO:
1. Số CCCD/CMND:
    - Chỉ được là số, không được bao gồm các ký tự chữ, ký tự đặc biệt.
2. Ngày sinh:
   - Chỉ được chọn ngày trong quá khứ, không được chọn ngày hiện tại và tương lai.
3. Mật khẩu:
   - Độ dài từ 8 - 16 ký tự.
   - Không được phép chứa khoảng trắng hay để trống.
   - Chứa ít nhất 1 ký tự thường, hoa, ký tự đặc biệt và 1 chữ số.
4. Ngày nhận và trả phòng:
   - Ngày nhận và trả phòng phải là ngày ở hiện tại và tương lai, không được nhập ngày trong quá khứ.
   - Ngày nhận phòng chỉ được chọn trong khoảng từ ngày hiện tại tới trước ngày trả phòng.
   - Ngày trả phòng chỉ được chọn trong khoảng từ sau ngày đặt phòng, không thể chọn trước ngày đặt phòng.
