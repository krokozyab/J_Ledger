/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans

import org.primefaces.event.data.SortEvent
import service.Car
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

@ApplicationScoped
@Named("dtSummaryRowView")
class SummaryRowView : Serializable {

    var cars: List<Car>? = null
        private set

    //@ManagedProperty("CarService")
    private var service = CarService()

    private val testFire = ArrayList<String>()


    @PostConstruct
    fun init() {
        cars = service.createCars(50)
        testFire.add("I'm not initialized!")
    }

    fun setService(service: CarService) {
        this.service = service
    }

    //return (int) (Math.random() * 100000);
    val randomPrice: List<String>
        get() = testFire

    fun setSortProperty(o: Any) {
        // filter value
        if (o is String) {
            testFire.add("set sort: " + o)
        }
    }

    fun onsort(event: SortEvent?) {
        //filter by
        val ax: String
        ax = event!!.sortColumn.headerText
        if (event != null) {
            testFire.add("get column: " + event.sortColumn.headerText as String + "  I'm Initialized")
        }
    }


}
