# HairStudioSystem

Run->Edit configurations -> VM Options:

--module-path
${PATH_TO_FX}
--add-modules=javafx.controls,javafx.fxml
--add-opens
javafx.base/com.sun.javafx.runtime=ALL-UNNAMED
--add-opens
javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED
--add-opens
javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.binding=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.event=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED

Settings -> Appearance & Behavior -> Path Variables:
PATH_TO_FX   C:\Program Files\Java\javafx-sdk-11.0.2\lib (example)

Libraries to download:

jdk 11.0.6
javafx-sdk-11.0.2
com.calendarfx:view:11.8.3
com.jfoenix:jfoenix:9.0.9
Hibernate 5.4.9-5.4.9
mysql:mysql-connector-java:8.0.17
org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Draft-7plus