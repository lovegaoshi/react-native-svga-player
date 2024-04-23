import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, ScrollView} from 'react-native';
import RNSvgaPlayer from 'react-native-svga-player';

export default () => {
  const files = [
    'Goddess',
    'Rocket',
    'matteBitmap',
    'heartbeat',
    'matteRect',
    'mutiMatte',
    'rose_2.0.0',
  ];
  return (
    <View>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Text style={styles.welcome}>Svga</Text>
        <View style={styles.container}>
          {files.map(f => (
            <View
              key={f}
              style={{
                width: '45%',
              }}>
              <RNSvgaPlayer
                style={{width: 200, height: 200}}
                source={`https://raw.githubusercontent.com/yyued/SVGAPlayer-iOS/master/SVGAPlayer/Samples/${f}.svga`}
              />
              <Text>{f}</Text>
            </View>
          ))}
        </View>
      </ScrollView>
    </View>
  );
};
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    marginTop: 80,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
