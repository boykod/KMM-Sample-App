//
//  UserView.swift
//  iosApp
//
//  Created by Dmytro on 13.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage


struct UserView: View {
    
    @ObservedObject var userState: UserState
    
    var body: some View {
        if (userState.isLoading) {
            loading
        } else {
            user
        }
    }
    
    private var loading: some View {
        Text("Loading...")
    }
    
    private var user: some View {
        VStack(alignment: .leading) {
            userName
            customDivider
            userCountry
            userFollows
            customDivider
            userRepo
            userGists
            Spacer()
        }
    }
    
    private var userName: some View {
        HStack(spacing: 12) {
            if (userState.userData.avatar.isEmpty) {
                Circle()
                    .fill(Color.blue)
                    .frame(width: 64, height: 64)
            } else {
                URLImage(
                    url: URL(string: userState.userData.avatar)!,
                    failure: { _,_ in
                        Circle()
                            .fill(Color.blue)
                            .frame(width: 64, height: 64)
                    },
                    content: { image in
                        image.resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 64, height: 64)
                            .clipShape(Circle())
                    }
                )
            }
            
            VStack(alignment: .leading, spacing: 8) {
                Text(userState.userData.name)
                    .font(.system(size: 18))
                    .fontWeight(.bold)
                Text(userState.userData.login)
                    .font(.system(size: 16))
                    .foregroundColor(.gray)
            }
            Spacer()
        }.padding(.top, 24).padding(.horizontal, 18)
    }
    
    private var userCountry: some View {
        VStack {
            Text(userState.userData.location)
                .padding(.top, 12)
                .padding(.horizontal, 18)
        }
    }
    
    private var userFollows: some View {
        HStack(spacing: 16) {
            HStack(spacing: 4) {
                Text(String(userState.userData.followers)).fontWeight(.bold)
                Text("followers")
            }
            
            HStack(spacing: 4) {
                Text(String(userState.userData.following)).fontWeight(.bold)
                Text("following")
            }
        }.padding(.top, 8).padding(.horizontal, 18)
    }
    
    private var userRepo: some View {
        VStack {
            HStack(spacing: 8) {
                Text("Repositories:")
                Text(String(userState.userData.publicRepos))
                Spacer()
                Image("RightArrow").frame(width: 12, height: 12)
            }.padding(.horizontal, 12)
            .padding(.vertical, 18)
            .background(Color("divider"))
            .cornerRadius(10)
        }.padding(.top, 12).padding(.horizontal, 18)
    }
    
    private var userGists: some View {
        VStack {
            HStack(spacing: 8) {
                Text("Gists:")
                Text(String(userState.userData.publicGists))
                Spacer()
                Image("RightArrow").frame(width: 12, height: 12)
            }.padding(.horizontal, 12)
            .padding(.vertical, 18)
            .background(Color("divider"))
            .cornerRadius(10)
        }.padding(.top, 4).padding(.horizontal, 18)
    }
    
    private var customDivider: some View {
        Divider().background(Color("divider")).padding(.horizontal, 18)
    }
}
