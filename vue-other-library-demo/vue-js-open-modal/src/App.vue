<template>
  <div>
    <button data-bs-toggle="modal" data-bs-target="#openModal" class="btn btn-primary" >Mở Modal</button>
<!--    <button @click="openModal">Open modal</button>-->
    <div class="modal fade" id="openModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">Thông tin đăng ký</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="fullName" class="form-label">Họ và tên</label>
                <input type="text" class="form-control" id="fullName" v-model="fullName">
              </div>
              <div class="mb-3">
                <label for="telephone" class="form-label">Số điện thoại</label>
                <input type="tel" class="form-control" id="telephone" v-model="telephone">
              </div>
              <div class="mb-3">
                <label for="dateOfBirth" class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" id="dateOfBirth" v-model="dateOfBirth">
              </div>
              <div class="mb-3">
                <label for="address" class="form-label">Địa chỉ</label>
                <input type="text" class="form-control" id="address" v-model="address">
              </div>
            </form>
          </div>
          <div class="modal-footer">

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import 'bootstrap';
export default {
  data() {
    return {
      fullName: '',
      telephone: '',
      dateOfBirth: '',
      address: ''
    };
  },
  mounted() {
    // Thêm sự kiện DOM để mở modal
    // document.getElementById('openModalBtn').addEventListener('click', this.openModal);
    //
    // // Thêm sự kiện DOM để đóng modal
    // document.getElementById('closeModalBtn').addEventListener('click', this.closeModal);
    // document.getElementById('closeModalBtn2').addEventListener('click', this.closeModal);
    //
    // // Thêm sự kiện DOM để lưu dữ liệu
    // document.getElementById('saveDataBtn').addEventListener('click', this.saveData);
  },
  beforeUnmount() {
    // Gỡ bỏ sự kiện DOM khi component bị hủy
    // document.getElementById('openModalBtn').removeEventListener('click', this.openModal);
    // document.getElementById('closeModalBtn').removeEventListener('click', this.closeModal);
    // document.getElementById('closeModalBtn2').removeEventListener('click', this.closeModal);
    // document.getElementById('saveDataBtn').removeEventListener('click', this.saveData);
  },
  methods: {
    openModal(){
      document.getElementById('openModal').addEventListener('click', this.openModal);
    }
    // openModal() {
    //   const modal = new bootstrap.Modal(document.getElementById('exampleModal'));
    //   modal.show();
    // },
    // closeModal() {
    //   const modal = bootstrap.Modal.getInstance(document.getElementById('exampleModal'));
    //   if (modal) {
    //     modal.hide();
    //   }
    // },
    // saveData() {
    //   console.log("Dữ liệu đã lưu:", {
    //     fullName: this.fullName,
    //     telephone: this.telephone,
    //     dateOfBirth: this.dateOfBirth,
    //     address: this.address
    //   });
    //   this.closeModal(); // Đóng modal sau khi lưu
    // }
  }
};
</script>

<style>
/* Bạn có thể thêm các kiểu tùy chỉnh tại đây */
</style>
<!--<template>-->
<!--  <div>-->
<!--    <button class="btn btn-primary" @click="handleButtonClick">Mở Modal</button>-->

<!--    &lt;!&ndash; Modal &ndash;&gt;-->
<!--    <div class="modal fade" id="openModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">-->
<!--      <div class="modal-dialog">-->
<!--        <div class="modal-content">-->
<!--          <div class="modal-header">-->
<!--            <h5 class="modal-title" id="modalLabel">Thông tin đăng ký</h5>-->
<!--            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>-->
<!--          </div>-->
<!--          <div class="modal-body">-->
<!--            <p>Nội dung modal ở đây.</p>-->
<!--          </div>-->
<!--          <div class="modal-footer">-->
<!--            <button type="button" class="btn btn-secondary" @click="closeModal">Đóng</button>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  methods: {-->
<!--    handleButtonClick() {-->
<!--      // Mở modal bằng cách thêm lớp 'show' và thay đổi thuộc tính 'aria-hidden'-->
<!--      const modalElement = document.getElementById('openModal');-->
<!--      modalElement.classList.add('show');-->
<!--      modalElement.style.display = 'block';-->
<!--      modalElement.setAttribute('aria-hidden', 'false');-->

<!--      // Đảm bảo rằng modal được thêm vào DOM-->
<!--      document.body.appendChild(modalElement);-->
<!--    },-->
<!--    closeModal() {-->
<!--      const modalElement = document.getElementById('openModal');-->
<!--      modalElement.classList.remove('show');-->
<!--      modalElement.style.display = 'none';-->
<!--      modalElement.setAttribute('aria-hidden', 'true');-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--/* Bạn có thể thêm các kiểu tùy chỉnh tại đây */-->
<!--</style>-->