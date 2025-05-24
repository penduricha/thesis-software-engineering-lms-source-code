
export default class ManageDateTime {

    getCurrentWeek() {
        const today = new Date();
        const currentDay = today.getDay();
        const startOfWeek = new Date(today);
        startOfWeek.setDate(today.getDate() - (currentDay === 0 ? 6 : currentDay - 1));
        const week = [];
        for (let i = 0; i < 7; i++) {
            const day = new Date(startOfWeek);
            day.setDate(startOfWeek.getDate() + i);
            const dayName = day.toLocaleString('en-US', { weekday: 'short' });
            const dayNumber = day.getDate();
            week.push({
                dayName: dayName,
                dayNumber: dayNumber,
                date: day,
                monthNumber: day.getMonth(),
                monthName: this.getMonthName_By_MonthNumber(day.getMonth()+1),
                year: day.getFullYear(),
            });
        }
        return week;
    }

    getWeek_By_Day(day) {
        // Lấy chỉ số ngày trong tuần
        const chooseDay = day.getDay();
        // Khởi tạo ngày bắt đầu tuần dựa trên ngày đã cho
        const startOfWeek = new Date(day);
        // Tính ngày đầu tuần (Thứ Hai)
        startOfWeek.setDate(day.getDate() - (chooseDay === 0 ? 6 : chooseDay - 1));
        const week = [];
        for (let i = 0; i < 7; i++) {
            const currentDay = new Date(startOfWeek);
            currentDay.setDate(startOfWeek.getDate() + i);
            const dayName = currentDay.toLocaleString('en-US', { weekday: 'short' });
            const dayNumber = currentDay.getDate();
            const monthNumber = currentDay.getMonth() + 1;
            const monthName = this.getMonthName_By_MonthNumber(monthNumber);
            const year = currentDay.getFullYear();
            week.push({
                dayName: dayName,
                dayNumber: dayNumber,
                date: currentDay,
                monthNumber: monthNumber,
                monthName: monthName,
                year: year,
            });
        }
        return week;
    }

    getMonthName_By_MonthNumber(monthNumber) {
        const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        if (monthNumber < 1 || monthNumber > 12) {
            throw new Error("Month number must be between 1 and 12");
        }
        return months[monthNumber - 1];
    }

    formatDateTime(date) {
        /// Cộng thêm 7 giờ vào thời gian hiện tại
        // Chuyển đổi đối tượng Date thành định dạng ISO cho input datetime-local
        // Lấy phần năm-tháng-ngàyGiờ:Phút
        date.setHours(date.getHours() + 7);
        return date.toISOString().slice(0, 16);
    }

    getDate_Choose(day) {
        if (!(day instanceof Date) || isNaN(day)) {
            throw new Error("Invalid date provided");
        }
        const dayName = day.toLocaleString('en-US', { weekday: 'short' });
        const dayNumber = day.getDate();
        // const month = day.toLocaleString('en-US', { month: 'short' });
        // const year = day.getFullYear();
        return {
            dayName: dayName,
            dayNumber: dayNumber,
            date: day,
            monthNumber: day.getMonth(),
            monthName: this.getMonthName_By_MonthNumber(day.getMonth()+1),
            year: day.getFullYear(),
        };
    }

    getCurrentDayInfo() {
        const today = new Date();
        const dayName = today.toLocaleString('en-US', { weekday: 'short' });
        const dayNumber = today.getDate();

        return {
            dayName: dayName,
            dayNumber: dayNumber,
            date: today,
            monthNumber: today.getMonth()+1,
            monthName: this.getMonthName_By_MonthNumber(today.getMonth()+1),
            year: today.getFullYear(),
        };
    }

    getListDayOfWeek() {
        return this.getCurrentWeek();
    }
}
