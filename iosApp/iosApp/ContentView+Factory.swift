//
//  ContentView+Factory.swift
//  iosApp
//
//  Created by Dmytro on 12.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit

extension ContentView {
    static func make() -> Self {
        let state = ContentState()
        
        return ContentView(state: state)
    }
}
