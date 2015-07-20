# TremorSense
Hey there, and welcome to TremorSense. Here you'll find the official files used to build the TremorSense android app for measurement of pathological tremor. I'm using GitHub because for me TremorSense is about providing the best end-user (and *patient*) experience - this means excluding adds, in-app purchases, etc. I want this to be a clean program, ladies and gentlemen!

## What is it?
It is an app for monitoring tremor over time in patients with Parkinson's disease, Essential Tremor, anxiety disorders, and other tremor-inducing neurological conditions. It has a nice, simple set of 3 main options.

## Where are we at?
You join us at an exciting time where the final vision for the app is slowly beginning to come together. At the moment, the basic schema for how tremor is measured is complete. Currently, we're using exponent-transformed standard deviation measurements. *The app functions,* it's just not ready for release (by a long shot). So we're working on that.

### What's left to be done before release?

* I want to explore the possibility of using an alternate system that basically measures the Abs(dX) of the accelerometer data and averages that.

* Implement **settings**:

** **Countdown timer length**: some patients may have issues getting the test ready in the three seconds afforded to you by default, so this would allow them to change that value.

* **Implement SQL** to save user data. The beginnings of this can be seen in the initial commit. Eventually, we want to be able to *save userdata on completion of a full test*, and *load userdata on profile page*.

* Figure out how to do **custom tests**. As the on-lap tests are kind of useless to the majority of patients, and a postural tremor test (with arms extended forward) may be impractical for severe cases, custom test positions may be set up. I'm still working through this one. We also want to *save custom test preferences between sessions*.

## Standard dev-... dude, how does it work?

When the recording activity is initialised, the device's Z-axis accelerometer is turned on to it's fastest-reporting setting. A 1000-value array is initialised (because that takes a reasonable amount of time and covers a good number of data points). 

## How can I help?
If you're reading this, it's very, very probable that you're better at programming than me. I literally learned Java specifically for this project so... there are holes. If you want to help improve the app, send me a message and get involved!

## You suck. Who do I contact?
I appreciate any and all feedback. You can send me a message on GitHub, or at notanavocado@gmail.com.
