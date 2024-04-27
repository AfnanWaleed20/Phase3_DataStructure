package application;
//this class cotains a date its used as a object in node Avl2
import java.util.Date;

import javafx.scene.chart.PieChart.Data;

public class Date_Stack {
 Date date;

public Date_Stack(Date date) {
	super();
	this.date = date;
}
public Date_Stack() {
	super();

}
public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}
@Override
public String toString() {
	return "Date_Stack [date=" + date + "]";
}


}
