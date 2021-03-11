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

    @Published var userData = GithubUserResponse(
        login: "",
        url: "",
        avatar: "",
        name: "",
        email: nil,
        followers: 0,
        following: 0,
        location: "",
        publicRepos: 0,
        publicGists: 0
    )
    
    private lazy var userRepository: UserRepository = {
        let userRepository = UserRepository()
        userRepository.userView = self
        
        return userRepository
    }()
    
    init() {
        getUserByUsername(username: "JakeWharton")
    }
    
    func getUserByUsername(username: String) {
        userRepository.getUser(userName: username)
    }
    
    func updateUserData(data: GithubUserResponse) {
        userData = data
    }
    
    func openUserProfile() {
        URL(string: userData.url).map {
            UIApplication.shared.open($0)
        }
    }

}
