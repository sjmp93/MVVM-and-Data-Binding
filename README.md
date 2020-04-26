# MVVM-and-Data-Binding
Sample project for MVVM and Data Binding in Android experimentation


We will review a few MVVM concepts using a simple Activity that shows a status text and a button.

Using Data Binding, we will bind the TextView and the Button with it correspondant property in the ViewModel class.
ViewModel properties will be based on a data model that implements all the status related stuff.

The app consists on a status text with two possible values: "Enabled" or  "Disabled"; and a button wich, when pressed, will trigger a method from the ViewModel, that will modify the text value, updating the UI through an Observer.
