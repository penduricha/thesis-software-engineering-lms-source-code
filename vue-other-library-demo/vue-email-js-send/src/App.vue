<template>
  <form ref="form" @submit.prevent="sendEmail">
    <label>Name</label>
    <input type="text" name="user_name">
    <label>Email</label>
    <input type="email" name="user_email">
    <label>Message</label>
    <textarea name="message"></textarea>
    <input type="submit" value="Send">
  </form>
</template>

<script>
import emailjs from '@emailjs/browser';

export default {
  methods: {
    sendEmail() {
      emailjs
          .sendForm('service_3gqmp8p', 'template_6npwmge', this.$refs.form, {
            publicKey: 'ugEDRuTpbjK3YdH8h',
          })
          .then(
              () => {
                console.log('SUCCESS!');
                alert('Send successfully')
              },
              (error) => {
                console.log('FAILED...', error.text);
              },
          );
    },
  },
};
</script>