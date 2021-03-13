//
//  UserView+Factory.swift
//  iosApp
//
//  Created by Dmytro on 13.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit

extension UserView {
    static func make() -> Self {
        let state = UserState()
        
        return UserView(userState: state)
    }
}
