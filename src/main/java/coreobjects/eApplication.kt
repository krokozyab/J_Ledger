package coreobjects

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "applications")
class eApplication : Serializable {
    @Id
    @Column(name = "APPLICATION_ID")
    var applicationId: Int? = null
    @Column(name = "APPLICATION_NAME")
    var applicationName: String? = null

    internal constructor() {
    }

    internal constructor(applicationId: Int?, applicationName: String) {
        this.applicationId = applicationId
        this.applicationName = applicationName
    }

    override fun toString(): String {
        return applicationName!!
    }

    companion object {
        private val serialVersionUID = 1L
    }
}