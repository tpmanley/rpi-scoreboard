/**
 *  Raspberry Pi RGB LED Display
 *
 *  Copyright 2014 Tom Manley
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {

    definition (name: "RPi RGB LED Display", namespace: "tpmanley", author: "Tom Manley") {

        capability "Notification"
        capability "Actuator"
        
        command "clear_display"
        command "test"
    }

    tiles {
        standardTile("displayTile", "device.display", width: 2, height: 2) {
			state "on", label: '${name}', action: "clear_display", icon: "st.switches.light.on", backgroundColor: "#79b821", nextState: "off"
            state "off", label: '${name}', action: "test", icon: "st.switches.light.off", backgroundColor: "#79b821", nextState: "on"
        }
        main(["displayTile"])
        details(["displayTile"])
    }
}

def deviceNotification(msg) {
	log.trace "Displaying text: '${msg}'"
    get("/pi/display/text/${msg}")
}

def clear_display() {
    log.trace "Clearing screen"
    get("/pi/display/text/")
}

def test() {
    get("/pi/display/text/test")
}

private Integer convertHexToInt(hex) {

    Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {

    [convertHexToInt(hex[0..1]),
     convertHexToInt(hex[2..3]),
     convertHexToInt(hex[4..5]),
     convertHexToInt(hex[6..7])].join(".")
}

private getHostAddress() {

    //def parts = device.deviceNetworkId.split(":")
    //def ip = convertHexToIP(parts[0])
    //def port = convertHexToInt(parts[1])

    //return ip + ":" + port
    return "172.17.3.122:5000"
}

private get(path) {
    log.debug "GET ${path}"

    def result = new physicalgraph.device.HubAction(
            method: "GET",
            path: path,
            headers: [
            	HOST:getHostAddress()
            ]
    )
    return result
}

/*

def locationHandler(evt) {
    def description = evt.description
    def hub = evt?.hubId

    def parsedEvent = parseEventMessage(description)
    parsedEvent << ["hub":hub]

//        log.trace "evt"+evt
    log.trace parsedEvent
}

def turnOff() {

    get("/pi/display/1/off")
}

def openDoor() {

    get("/pi/display/1/on")
}

private getCallBackAddress() {

    device.hub.getDataValue("localIP") + ":" + device.hub.getDataValue("localSrvPortTCP")
}

private Integer convertHexToInt(hex) {

    Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {

    [convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}

private getHostAddress() {

    def parts = device.deviceNetworkId.split(":")
    def ip = convertHexToIP(parts[0])
    def port = convertHexToInt(parts[1])

    return ip + ":" + port
}

private get( path) {
    log.debug "GET ${path}"

    def result = new physicalgraph.device.HubAction(
            method: "GET",
            path: path,
            headers: [
            	HOST:getHostAddress()
            ]
    )
    return result
}

private subscribe( path) {

    log.debug "SUBSCRIBE ${path}"

    def result = new physicalgraph.device.HubAction(
            method: "SUBSCRIBE",
            path: path,
            headers: [HOST:getHostAddress()]
    )
}

private def parseEventMessage(String description) {
    def event = [:]
    def parts = description.split(',')
    parts.each { part ->
        part = part.trim()

        if (part.startsWith('headers')) {
            part -= "headers:"
            def valueString = part.trim()
            if (valueString) {
                event.headers = valueString
            }
        }
        else if (part.startsWith('body')) {
            part -= "body:"
            def valueString = part.trim()
            if (valueString) {
                event.body = valueString
            }
        }
    }

    event
}

*/

private parse(String description) {
    log.debug "Parsing '${description}'"
/*
    def parsedEvent= parseEventMessage( description)

    def headerString = new String(parsedEvent.headers.decodeBase64())
    def bodyString = new String(parsedEvent.body.decodeBase64())

    def json = new groovy.json.JsonSlurper().parseText( bodyString)

    log.trace json

    if( json.msg)
    {
        if( json.msg.startsWith("state"))
        {
            log.trace "Setting state"

            sendEvent (name: json.name, value: json.state)
        }
        else if( json.msg.startsWith("status"))
        {
            log.trace "Setting state from status"

            sendEvent (name: "door",  value: json.door)
        }
    }
    */
}

