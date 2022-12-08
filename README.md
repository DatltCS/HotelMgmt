
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
  - Giao diện chính của khách hàng tương tự với giao chính khi chưa đăng nhập, đồng thời đóng vai trò màn hình cho chức năng Tìm kiếm đặt phòng của khách hàng. Ngoài ra trên góc trên bên phải sẽ có dòng chữ "Welcome, user_name" hiển thị tên người dùng và đóng vai trò làm menu hiển thị các chức năng của khách hàng.
  - Người dùng sẽ có 2 chức năng chính là: Đặt phòng và Đặt dịch vụ phòng cho những phòng đã được đặt.
  - **Chức năng đặt phòng:**
    - Bao gồm các ô nhập ngày check-in và check-out, nút xử lý sự kiện tìm đặt phòng.
    - Người dùng phải nhập đủ ngày check-in và check-out trước khi bấm nút Tìm kiếm, nếu không sẽ phát sinh lỗi "Please fill out all field!"
    - Ngày check-in và check-out chỉ có thể được chọn từ hộp chọn của chương trình, không thể tự nhập. Yêu cầu đầu vào của ngày check-in và check-out được mô tả ở phần **"RÀNG BUỘC DỮ LIỆU ĐẦU VÀO"**.
    - Sau khi bấm nút Tìm kiếm, màn hình chính sẽ chuyển sang giao diện tìm kiếm bao gồm: các ô nhập ngày check-in và check-out mà người dùng đã nhập và truyền vào từ cửa sổ trước, các Button hiển thị thông tin các phòng tồn tại để đặt trong thời gian khách hàng chọn.
    - Khi người dùng nhấp và thông tin của 1 phòng, cửa sổ Booking cho phòng đó sẽ hiện lên và hiển thị các thông tin cơ bản về phòng. Thông qua cửa sổ Booking, người dùng có thể tùy chọn loại hình dịch vụ phòng kèm theo và thấy được tổng số tiền dự kiến. Người dùng bấm vào nút **"Finish Booking"** để hoàn tất đặt phòng đó.
    - Sau khi đặt phòng thành công hay thất bại, cửa sổ Booking sẽ tự động đóng lại và mở ra thông báo hiển thị tình trạng đặt phòng của người dùng (thành công/thất bại).
  - **Chức năng đặt dịch vụ phòng:**
    - Cửa sổ đặt dịch vụ phòng bao gồm: combo box hiển thị danh sách các booking người dùng đã đặt, 1 table hiển thị tên các dịch vụ phòng sẵn có và 1 table hiển thị thông tin các dịch vụ đã được đặt trong booking người dùng chọn, thêm vào đó là 3 nút xử lý (thêm dịch vụ, xóa dịch vụ, xóa toàn bộ dịch vụ).
    - Combo box sẽ hiển thị những booking được khách hàng đó đã đặt và chưa hủy (hoặc check-out)
    - Khi khách hàng chọn mã booking trong combo box thì table thứ 2 sẽ hiển thị thông tin các dịch vụ đã được đặt trước đó của khách. Khách từ đó có thể tương tác thêm, xóa hoặc xóa tất cả dịch vụ thông qua 3 button cuối màn hình.
    - Để thêm dịch vụ phòng thì khách hàng cần phải nhấp chọn 1 dòng dữ liệu chứa thông tin dịch vụ phòng ở bảng dịch vụ sẵn có trước rồi mới nhất button Thêm dịch vụ, nếu không sẽ xuất hiện lỗi.
    - Để xóa đi 1 dịch vụ phòng mà mình đã đặt, khách hàng cần phải nhấp chọn dòng dịch vụ cần xóa ở trong bảng dịch vụ đã đặt, nếu không hệ thống sẽ báo lỗi.
    - Khi chọn xóa tất cả dịch vụ thì hệ thống sẽ cảnh báo và chờ nhận phản hồi từ phía khách hàng, nếu khách hàng đồng ý thì mới tiến hành xóa, không thì sẽ giữ nguyên.

### 2. Vai trò nhân viên khách sạn:
  - Giao diện chính của nhân viên sẽ bao gồm 2 button hiển thị 2 chức năng chính: Tra cứu thông tin đặt phòng của khách (và thực hiện check-in, check-out) và Cập nhật đặt dịch vụ phòng cho khách. Ngoài ra còn có menu các chức năng chính của nhân viên ở phía trên tay trái, tương tự với khách hàng.
  - **Chức năng tra cứu thông tin đặt phòng và check-in, check-out khách hàng:**
    - Cửa sổ của chức năng tra cứu thông tin sẽ gồm 1 ô để nhân viên nhập số CMND/CCCD của khách hàng cần tra cứu. Khi nhập thông tin xong thì ấn vào nút Search để thực hiện tra cứu.
    - Nếu CCCD/CMND hợp lệ thì sẽ hiển thị ra bảng thông tin các lần booking của khách từ trước tới giờ. Và nhân viên sẽ được thực hiện các thao tác bằng 3 button được cung cấp: Check-in, Check-out, Mark as Canceled. Tuy nhiên không thể tự tiện chuyển đổi trạng thái booking của khách, chỉ những chuyển đổi hợp lệ mới được cho phép, vd:
      - Booked -> Check-in
      - Booked -> Canceled
      - Check-in -> Check-out
      - Check-in -> Canceled
    - Nếu thao tác chọn chuyển đổi trạng thái của nhân viên không hợp lệ, hệ thống sẽ báo lỗi.
  - **Chức năng Cập nhật đặt dịch vụ phòng cho khách:**
    - Tương tự như chức năng đặt dịch vụ phòng của khách hàng, nhưng nhân viên sẽ phải tự nhập vào ô tìm kiếm mã booking của khách hàng cung cấp để bổ sung dịch vụ phòng. Nếu mã booking đã được check-out hay cancel thì được tính là mã không hợp lệ, trong trường hợp đó hệ thống sẽ báo lỗi.

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
