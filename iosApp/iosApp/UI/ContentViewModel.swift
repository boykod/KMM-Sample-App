//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Dmytro on 10.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Combine
import shared

final class ContentViewModel: ObservableObject, UserView {

    @Published private(set) var userData = "title_userdata"
    
    private lazy var userRepository: UserRepository = {
        let userRepository = UserRepository()
        userRepository.userView = self
        
        return userRepository
    }()
    
    init() {
        getUserByUsername(username: "boykod")
    }
    
    func getUserByUsername(username: String) {
        userRepository.getUser(userName: username)
    }
    
    func updateUserData(data: GithubUserResponse) {
        userData = data.description()
    }

}
