Ovidiu Balaban Professional Portfolio
=================

A portfolio app illustrating Android modern development with Android Jetpack and Material Design.

Architecture
------------

The app is built using MVVM architecture. The model is managed by Room and accessed through by a repository layer. The repository layer is made up of a local repository(DAO-SQLite) and a remote repository(Firebase). The data is passed from the model to the UI though a ViewModel. 

Getting Started
---------------
Compile and run. Maybe in some emulators the circular images doesn't render very well, so it would be better to test the app on a physical terminal. 

Libraries Used
--------------
* firebase
* kotlinx-coroutines
* dagger 2
* room
* lifecycle
* lifecycle-extensions
* navigation-component
* android-material
* databinding
* glide 4
* threetenabp
* junit
* truth

License
-------

Copyright 2020 Ovidiu Balaban, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.