<template>
  <div class="home">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script lang="ts">
import HelloWorld from '@/components/HelloWorld.vue';
import { Vue, Component } from 'vue-property-decorator';
import Html2PdfService from '@/domain/service/html2pdf.service';

@Component({ components: { HelloWorld } })
export default class Home extends Vue {
  getPdf() {
    Html2PdfService.getPdf();
  }
}
</script>
<style lang="sass" scoped>

</style>
