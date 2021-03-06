# Android-practice-2-ImplicitIntents

![Alt Text](https://github.com/RobinKim-SWEngineer/Images-for-document/blob/master/ImplicitIntents.gif)

Unlike explicit intent where we provide the current context and target component, **Implicit intent** needs type of action and data for that action specified. Later on Android system examines other app's **intent-filters** in AndroidManifest.xml file and finds activity that *can handle* that implicit intent, and if so, we start the Activity.

We can also think about the other way around. We can allow our app to respond to ( or receive ) an implicit intent *sent from some other app* by specifying it through **intent-filters** in the AndroidManifest.xml file.

## Related methods
- The look of constructor is same as that of explicit intent, but parameters are different  :
 `Intent(String action, Uri uri)` 
   The primary pieces of implicit intent are **action** and **data**. Notice that here we don't mention target activity.

- After defining implcit intent, we ask the system to examine other apps whether they have any activity that can handle our intent  :
`intent.resolveActivity(PackageManager pm)` 
    Above method needs an instance of PackageManager, which is provided by `getPackageManager()`

- If there's activity that is matching our implicit intent, the component name will be returned and we can start the activity.
    ```
    if(intent.resolveActivity(getPackageManager()) != null) {
        startAcvitity(intent)
    } else {
        Log.d(tag: "ImplicitIntents", msg: "Couldn't find any qualified activity to handle this intent")
    }
    ```
- **ShareCompat.IntentBuilder** is a helper class for constructing sharing intents. Upon getting an IntentBuilder instance via 
  `.from(Activity launchingActivity)`, we subsequently call setters for necessary information and finally, start a chooser.
  
    ```
    ShareCimpat.IntentBuilder.from(this)
                             .setType("text/plain")
                             .setCooserTitle("Choose!")
                             .setText(sharingString)
                             .startChooser();
    ```



>> Since Android 11 ( API level 30 or higher ) there is default system **visibility filtering** which affects the return result of methods that query for the information about the other apps on the device. In such case, we need to **declare package visibility needs** so our application can access other apps.


For more detail implementation of **Implicit Intent** for each step please refer to the attached example.
