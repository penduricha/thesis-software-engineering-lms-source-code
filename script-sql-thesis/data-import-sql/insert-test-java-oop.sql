use project_thesis_final;

insert into bank_test_java_oop (description_test, image_diagram, name_test)
values ('Tiền điện chiếm một phần không nhỏ trong chi phí sinh hoạt của nhiều gia đình. Một chi nhánh cần quản lý các khách hàng tiêu thụ điện thông tin cần quản lý gồm: mã khách, họ tên khách, số kw điện tiêu thụ, địa chỉ, điện thoại, tháng tiệu thụ. Bên cạnh việc sử dụng các thiết bị làm sao cho tiết kiệm điện thì cách tính tiền điện sinh hoạt cũng là mối quan tâm hàng đầu luôn được khách hàng truy vấn thường xuyên nên chi nhánh có yêu cầu một ứng dụng nhỏ có các yêu cầu sau đây.\n
Câu 1 (2.5đ): Xây dựng class đối tượng cần thiết, thiết đặt các phương thức set/get để đưa thông tin vào đối tượng, nếu mã là rỗng thì ném lỗi ra ngoài.\n
Câu 2 (1.5đ): Xây dựng phương thức mức tính tiền điện theo nguyên tắc:\n
a.Nếu sử dụng điện trong khoảng định mức (số định mức100kw đầu tiên) thì tiền = tổng số kw * 1200.\n
b.Nếu sử dụng điện vượt ngoài định mức thì tiền =tiển trong định mức + (tổng số kw - định mức) * 1700.\n
Câu 3 (4đ): Xây dựng class danh sách chứa danh sách khách hàng, có các phương thức:\n
a)Thêm khách hàng, khi trùng mã thì ném lỗi và không được thêm.\n
b)Tìm kiếm khách hàng khi biết mã, trả về đối tượng tìm thấy.\n
c)Lấy danh sách khách hàng tiền phải trả>1000000.\n
d)Sửa thông tin khách hàng khi biết mã (chỉ cần sửa số điện thoại, địa chỉ).\n
Câu 4 (2đ): Xây dựng class chứa phương thức main cho phép thực thi kiểm tra các thao tác trên. Yêu cầu khi chạy chương trình thì tạo cứng 6 khách hàng trong đó có 3 khách hàng có số tiền >1000000, mỗi lần thao tác chức năng xuất thông tin danh sách.\n',
      'https://www.drawio.com/assets/img/blog/class-diagram-example.png',
        'Test 1'
);