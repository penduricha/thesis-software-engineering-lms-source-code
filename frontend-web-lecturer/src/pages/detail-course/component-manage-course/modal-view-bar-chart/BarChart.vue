
<template>
  <div class="card flex justify-center">
    <Chart
        type="bar"
        :data="chartData"
        :options="chartOptions"
        class="style-bar-chart"
        :width="700"
        :height="350"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import Chart from 'primevue/chart';


onMounted(() => {
  chartData.value = setChartData();
  chartOptions.value = setChartOptions();
});

const chartData = ref();
const chartOptions = ref();
const props = defineProps({
  chartProps:  {
    type: Array,
    required: true,
  },
});

const setChartData = () => {
  return {
    labels: props.chartProps.map(item => item.markRange),
    datasets: [
      {
        label: 'Student',
        data: props.chartProps.map(item => item.count),
        backgroundColor: ['#2C2C84', '#2C2C84', '#2C2C84','#2C2C84', '#2C2C84', '#2C2C84','#2C2C84', '#2C2C84', '#2C2C84','#2C2C84'],
        borderColor: ['#2C2C84', '#2C2C84', '#2C2C84','#2C2C84', '#2C2C84', '#2C2C84','#2C2C84', '#2C2C84', '#2C2C84','#2C2C84'],
        borderWidth: 1
      }
    ]
  };
};
const setChartOptions = () => {
  const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--p-text-color');
  const textColorSecondary = documentStyle.getPropertyValue('--p-text-muted-color');
  const surfaceBorder = documentStyle.getPropertyValue('--p-content-border-color');

  return {
    plugins: {
      legend: {
        labels: {
          color: textColor
        }
      }
    },
    scales: {
      x: {
        ticks: {
          color: textColorSecondary
        },
        grid: {
          color: surfaceBorder
        }
      },
      y: {
        beginAtZero: true,
        ticks: {
          color: textColorSecondary
        },
        grid: {
          color: surfaceBorder
        }
      }
    }
  };
}
</script>
