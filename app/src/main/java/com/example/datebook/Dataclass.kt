package com.example.datebook

class Dataclass {
    var dataTitle:String? =null
    var datadescription:String? =null
    var datapriority:String? =null


    constructor(dataTitle:String?, datadescription:String?, datapriority:String?){
        this.dataTitle =dataTitle
        this.datadescription= datadescription
        this.datapriority =datapriority

    }
    constructor(){

    }

}