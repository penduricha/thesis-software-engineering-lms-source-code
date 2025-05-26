<script setup>
import { ref, onMounted } from "vue";
import Chart from 'primevue/chart';

const chartData = ref();
const chartOptions = ref();
const props = defineProps({
  chartProps:  {
    type: Array,
    required: true,
  },
});

onMounted(() => {
  chartData.value = setChartData();
  chartOptions.value = setChartOptions();
});

const setChartData = () => {
  //console.log("Props data: ", props.chartProps);
  return {
    labels: props.chartProps.map(item => item.markRange),
    datasets: [
      {
        data: props.chartProps.map(item => item.count),
        backgroundColor: ['#003f5c', '#7a5195', '#ef5675', '#ffa600'],
        hoverBackgroundColor: ['#003f5c', '#7a5195', '#ef5675', '#ffa600'],
      },
    ],
  };
};

const setChartOptions = () => {
  const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--p-text-color');

  return {
    plugins: {
      legend: {
        labels: {
          usePointStyle: true,
          color: textColor,
        },
      },
    },
  };
};
</script>
<template>
  <div class="card flex justify-center">
    <Chart type="pie" :data="chartData" :options="chartOptions" class="w-full md:w-[30rem]" />
  </div>
</template>